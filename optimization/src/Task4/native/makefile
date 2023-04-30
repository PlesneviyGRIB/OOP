lib.so: main.o
	gcc -shared -fPIC -o lib.so main.o -lc

main.o: main.c Task4_Main.h
	gcc -c -fPIC -D_POSIX_C_SOURCE -I/usr/lib/jvm/java-17-openjdk-amd64/include/ -I/usr/lib/jvm/java-17-openjdk-amd64/include/linux main.c -o main.o
