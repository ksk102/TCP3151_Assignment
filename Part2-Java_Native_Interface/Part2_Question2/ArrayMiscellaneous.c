#include <stdio.h>
#include <jni.h>

void showAllNumber(jint numArr[], int len){
  printf("All number in the array: ");

  for(int i = 0; i < len; i++){
    printf("%ld ", numArr[i]);
  }

  printf("\n");
}

void sortTheNumber(jint numArr[], int len){
  int temp;

  for(int i = 0; i < len; i++){
    for(int j = i + 1; j < len; j++){
      if(numArr[i] > numArr[j]){
        temp = numArr[i];
        numArr[i] = numArr[j];
        numArr[j] =temp;
      }
    }
  }

  printf("After sort: ");
  for(int i = 0; i < len; i++){
    printf("%ld ", numArr[i]);
  }

  printf("\n");
}

void calculateTotalMean(jint numArr[], int len){
  int total = 0;

  for(int i = 0; i < len; i++){
    total += numArr[i];
  }

  printf("Total of the array is %d\n", total);
  printf("Mean of the array is %.2f\n", (float)total / (float)len);
}

JNIEXPORT void JNICALL Java_ArrayGenerate_arrayMiscellaneous(JNIEnv *env, jobject object, jintArray numArray){
  // link the java array field, and get the array
  jsize len = (*env)->GetArrayLength(env, numArray);
  jint *numArr = (*env)->GetIntArrayElements(env, numArray, NULL);

  showAllNumber(numArr, len);
  sortTheNumber(numArr, len);
  calculateTotalMean(numArr, len);

  (*env)->ReleaseIntArrayElements(env, numArray, numArr, JNI_ABORT);
}