cd ./native
make
cd ..
javac Main.java Person.java
cd ..

ls
java -cp . -Djava.library.path=./Task4/native/ Task4.Main