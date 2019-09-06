#include <stdio.h>
#include <jni.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

// generate the array with random number [1-100] with given length
// amendment to the assignment question @ 20190904
// display all elements in the array instead of only less than 50
void GenerateArrayDisplayL50(jint *theArray, int length){
  bool numLessThan50 = false;

  printf("Numbers less than 50 in the array: ");

  for(int i = 0; i < length; i++){
    // generate the random numbers
    // (rand() % (MAX - MIN + 1)) + MIN
    theArray[i] = (rand() % (100 - 1 + 1)) + 1;

    // display the numbers that are less than 50
    if(theArray[i] < 50){
      numLessThan50 = true;
      printf("%ld ", theArray[i]);
    }
  }
  
  // if no number less than 50
  if(!numLessThan50){
    printf("not found");
  }

  printf("\n");
}

// generate the array with random number [1-100] with given length
// amendment to the assignment question @ 20190904
// display all elements in the array instead of only less than 50
void GenerateArray(jint *theArray, int length){
  int countLess50 = 0;
  printf("Numbers in the array: ");

  for(int i = 0; i < length; i++){
    // generate the random numbers
    // (rand() % (MAX - MIN + 1)) + MIN
    theArray[i] = (rand() % (100 - 1 + 1)) + 1;
    printf("%ld ", theArray[i]);

    // count the number of elements that are less than 50
    if(theArray[i] < 50){
      countLess50++;
    }
  }
  printf("\n");

  // display the number of elements less than 50
  printf("Number of elements less than 50: %d\n", countLess50);
}

JNIEXPORT jintArray JNICALL Java_ArrayGenerate_arrayGenerator(JNIEnv *env, jobject object, jint length){
  // c array
  jint randomNumber[length];
  // create a new java array
  jintArray javaArray;
  javaArray = (*env)->NewIntArray(env, length);

  // Use current time as seed for random generator 
  srand(time(0)); 

  // generate the array
  GenerateArray(randomNumber, length);

  // copy the native array to java array
  (*env)->SetIntArrayRegion(env, javaArray, 0, length, randomNumber);

  return javaArray;
}