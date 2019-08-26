#include <stdio.h>
#include <jni.h>
#include <math.h>

int findMaxNumber(jint theArray[], int length){
  int max;
  int index;
 
  max = theArray[0];
  index = 0;
 
  for (int i = 1; i < length; i++) {
    if (theArray[i] > max) {
       index = i;
       max = theArray[i];
    }
  }

  printf("The Maximum is %d at Index %d\n", max, index);

  return max;
}

int findMinNumber(jint theArray[], int length){
  int min;
  int index;
 
  min = theArray[0];
  index = 0;
 
  for (int i = 1; i < length; i++) {
    if (theArray[i] < min) {
       index = i;
       min = theArray[i];
    }
  }

  printf("The Minimum is %d at Index %d\n", min, index);

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