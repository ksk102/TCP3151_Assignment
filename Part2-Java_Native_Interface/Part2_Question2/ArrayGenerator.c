#include <stdio.h>
#include <jni.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

// generate the array with random number [1-100] with given length
void GenerateArray(jint *theArray, int length){
  bool numLessThan50 = false;

  printf("Numbers less than 50 in the array: \n");

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

JNIEXPORT void JNICALL Java_ArrayGenerate_arrayGenerator(JNIEnv *env, jobject object, jint length){
  jint randomNumber[length];

  // Use current time as seed for random generator 
  srand(time(0)); 

  // generate the array
  GenerateArray(randomNumber, length);

  // link with java class, to get java array field
  jclass clazz = (*env)->GetObjectClass(env, object);
  jfieldID fid = (*env)->GetFieldID(env, clazz, "arrayNum", "[I");
  jintArray javaArray = (*env)->GetObjectField(env, object, fid);

  // copy the native array to java array
  (*env)->SetIntArrayRegion(env, javaArray, 0, length, randomNumber);
}