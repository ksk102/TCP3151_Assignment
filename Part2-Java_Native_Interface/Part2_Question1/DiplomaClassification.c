#include <stdio.h>
#include <jni.h>
#include <string.h>

JNIEXPORT jstring JNICALL Java_ClassifyHonours_classifyDiploma(JNIEnv *env, jobject object, jdouble cgpa){
    char honour[15] = "Fail";

    if(cgpa >= 3.5){
        strncpy(honour, "Distinction", 15);
    }
    else if(cgpa >= 3.0){
        strncpy(honour, "Credit", 15);
    }
    else if(cgpa >= 2.0){
        strncpy(honour, "Pass", 15);
    }

    // convert char[] to java.lang.String
    return (*env)->NewStringUTF(env, honour);
}