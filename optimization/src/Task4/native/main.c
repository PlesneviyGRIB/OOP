#include <stdio.h>
#include <stdlib.h>
#include "Task4_Main.h"

struct myNumbers {
  char a;
  int b;
  long c;
};

JNIEXPORT void JNICALL Java_Task4_Main_memoryOverflow(JNIEnv *, jclass) {
  while(1) {
    malloc(1024 * 100);
  }
}

JNIEXPORT void JNICALL Java_Task4_Main_error(JNIEnv *, jclass) {
  int num = 1 / 0;
}

JNIEXPORT jint JNICALL Java_Task4_Main_getStringLength(JNIEnv *env, jclass cls, jstring str) {
  jsize length = (*env)->GetStringLength(env, str);

  return length;
}

JNIEXPORT void JNICALL Java_Task4_Main_printPersonInfo(JNIEnv *env, jclass cls, jobject obj) {

  jclass objcls = (*env)->GetObjectClass(env, obj);

  jmethodID mid = (*env)->GetMethodID(env, objcls, "toString", "()Ljava/lang/String;");

  if (mid != NULL) {
    jstring str = (*env)->CallObjectMethod(env, obj, mid);

    const char* strutf = (*env)->GetStringUTFChars(env, str, 0);
    printf("Person info: %s\n", strutf);
    (*env)->ReleaseStringUTFChars(env, str, strutf);
  }
}

JNIEXPORT void JNICALL Java_Task4_Main_changePersonAge(JNIEnv *env, jclass cls, jobject obj) {
  jclass objcls = (*env)->GetObjectClass(env, obj);

  jfieldID fid = (*env)->GetFieldID(env, objcls, "age", "I");

  if (fid != NULL) {
    (*env)->SetIntField(env, obj, fid, (jint)15);
  }
}

JNIEXPORT jlong JNICALL Java_Task4_Main_getStructPointer(JNIEnv *env, jclass cls) {
  struct myNumbers* nums = malloc(sizeof(struct myNumbers));

  if (nums == NULL)
    return -1;

  nums->a = 0;
  nums->b = 5;
  nums->c = 10;

  return (jlong)nums;
}

JNIEXPORT void JNICALL Java_Task4_Main_printStructByPointer(JNIEnv *env, jclass cls, jlong ptr) {
  struct myNumbers* nums = ((struct myNumbers*)ptr);


  printf("myNumbers struct values: %d, %d, %ld\n", nums->a, nums->b, nums->c);

  free(nums);
}