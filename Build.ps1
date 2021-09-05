Write-Output '[Build]Stardust Crusader Engine Build Script'
Write-Output '[Build]Gathering source files...'
# Create a temporary directory.
if(!(Test-Path 'obj')){
    mkdir 'obj'
}
Get-ChildItem -i *.java -r -n | Out-file obj/sources.txt
Write-Output '[Build]Building...'
javac -encoding UTF-8 -cp src -d bin '@obj/sources.txt'
Write-Output '[Build]Done.'