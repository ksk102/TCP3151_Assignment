#include <stdio.h>
#include <jni.h>
#include <string.h>

JNIEXPORT jstring JNICALL Java_ClassifyHonours_classifyDegree(JNIEnv *env, jobject object){
    char honour[15] = "Fail";

    // just to demostrate another way to get cgpa'value
    // To get cgpa's value from Java
    jclass clazz = (*env)->GetObjectClass(env, object);
    jfieldID fid = (*env)->GetFieldID(env, clazz, "jCgpa", "D");
    jdouble cgpa = (*env)->GetDoubleField(env, object, fid);

    if(cgpa >= 3.67){
        strncpy(honour, "First", 15);
    }
    else if(cgpa >= 3.33){
        strncpy(honour, "Second Upper", 15);
    }
    else if(cgpa >= 2.67){
        strncpy(honour, "Second Lower", 15);
    }
    else if(cgpa >= 2.0){
        strncpy(honour, "Third", 15);
    }

    // convert char[] to java.lang.String
    return (*env)->NewStringUTF(env, honour);
}