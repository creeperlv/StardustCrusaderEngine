if(!(Test-Path 'obj')){
    mkdir 'obj'
}
dir -i *.java -r -n | Out-file obj/sources.txt
javac -encoding UTF-8 -cp src -d bin '@obj/sources.txt'