#include <stdio.h>
#include <jni.h>
#include <math.h>

int findMaxNumber(jint theArray[], int length){
  int max;
  int index;
  int counter;
 
  max = theArray[0];
  index = 0;
  counter = 1;
 
  for (int i = 1; i < length; i++) {
    if (theArray[i] > max) {
       index = i;
       counter = 1;
       max = theArray[i];
    }
    else if(theArray[i] == max){
      counter++;
    }
  }

  printf("The Maximum is %d, at Index %d, Occurs %d times\n", max, index, counter);

  return max;
}

int findMinNumber(jint theArray[], int length){
  int min;
  int index;
  int counter;
 
  min = theArray[0];
  index = 0;
  counter = 1;
 
  for (int i = 1; i < length; i++) {
    if (theArray[i] < min) {
       index = i;
       counter = 1;
       min = theArray[i];
    }
    else if(theArray[i] == min){
      counter++;
    }
  }

  printf("The Minimum is %d, at Index %d, Occurs %d times\n", min, index, counter);

  return min;
}

JNIEXPORT int JNICALL Java_ArrayGenerate_arrayMinMaxDiff(JNIEnv *env, jobject object, jintArray numArray){
  int max;
  int min;

  // link the java array field, and get the array
  jsize len = (*env)->GetArrayLength(env, numArray);
  jint *numArr = (*env)->GetIntArrayElements(env, numArray, NULL);

  max = findMaxNumber(numArr, len);
  min = findMinNumber(numArr, len);

  (*env)->ReleaseIntArrayElements(env, numArray, numArr, JNI_ABORT);

  return max - min;
}