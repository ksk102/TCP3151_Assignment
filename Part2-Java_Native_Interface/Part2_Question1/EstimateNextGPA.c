#include <stdio.h>
#include <jni.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>

// evaluate whether the string is integer
int isInteger(char credit[]){
    bool valid = false;
    int len = strlen(credit);
    
    // strip trailing newline or other white space
    while (len > 0 && isspace(credit[len - 1])){
        len--;
    }
        
    if (len > 0)
    {
        valid = true;
        for (int i = 0; i < len; i++)
        {
            if (!isdigit(credit[i]))
            {
                valid = false;
                break;
            }
        }
    }
    
    return valid;
}

// get the credit hours earned
int getCompletedCredit(){
    char completedCreditString[15];
    bool error;

    do{
        error = false;

        // get input from user
        printf("Your completed credit hours: ");
        scanf("%s", completedCreditString);

        // if the input is not positive integer, prompt error message, and get the user input again
        if(!isInteger(completedCreditString)){
            printf("\nInvalid input! Positive integer only!\n");
            error = true;
        }
    } while(error);

    // convert string to int
    return atoi(completedCreditString);
}

// get the currently taking credit hours by the users
int getCurrentCredit(){
    char currentCreditString[15];
    bool error;

    do{
        error = false;

        // get input from user
        printf("Your current credit hours: ");
        scanf("%s", currentCreditString);

        // if the input is not positive integer, prompt error message, and get the user input again
        if(!isInteger(currentCreditString)){
            printf("\nInvalid input! Positive integer only!\n");
            error = true;
        }
    } while(error);

    // convert string to int
    return atoi(currentCreditString);
}

// get the target cgpa user wish to achieve
float getTargetCgpa(JNIEnv *env, jobject object, jdouble currentCgpa){
    jdouble targetCgpa;
    char targetCgpaString[15];
    bool error;
    
    // connect the java's method
    jclass clazz = (*env)->GetObjectClass(env, object);
    jmethodID mid = (*env)->GetMethodID(env, clazz, "validDecimalPlaces", "(DI)Z");

    do{
        error = false;

        // get user input
        printf("Your target CGPA for this trimester: ");
        scanf("%s", targetCgpaString);
        // convert user input to double
        targetCgpa = atof(targetCgpaString);
        
        // if the input is lesser than current cgpa or greater than 4, prompt error, and get user input again
        if(targetCgpa < currentCgpa || targetCgpa > 4){
            printf("\nInvalid input! CGPA should be within (%.2f - 4.00)!\n", currentCgpa);
            error = true;
        }
        // if the cgpa inserted is not 2 decimal places
        else if(!(*env)->CallBooleanMethod(env, object, mid, targetCgpa, 2)){
            printf("\nInvalid input! CGPA must be within 2 decimal places!\n");
            error = true;
        }
    } while(error);

    return targetCgpa;
}

// calculate the gpa to get in this trimester in order to get the target cgpa
float calculateGpaToGet(float currentCgpa, int completedCredit, int currentCredit, float targetCgpa){
    return ((targetCgpa * (currentCredit + completedCredit)) - (currentCgpa * completedCredit)) / currentCredit;
}

JNIEXPORT void JNICALL Java_ClassifyHonours_estimateNextGPA(JNIEnv *env, jobject object, jdouble currentCgpa){
    int completedCredit;
    int currentCredit;
    float targetCgpa;
    float gpaToGet;

    // display the current cgpa
    printf("Your current CGPA: %.2f\n", currentCgpa);
    // get the credit hours earned
    completedCredit = getCompletedCredit();
    // get the currently taking credit hours by the users
    currentCredit = getCurrentCredit();
    // get the target cgpa user wish to achieve
    targetCgpa = getTargetCgpa(env, object, currentCgpa);
    // calculate the gpa to get in this trimester in order to get the target cgpa
    gpaToGet = calculateGpaToGet(currentCgpa, completedCredit, currentCredit, targetCgpa);

    // display to result
    printf("You should get a GPA of %.2f to raise your CGPA to %.2f", gpaToGet, targetCgpa);
}