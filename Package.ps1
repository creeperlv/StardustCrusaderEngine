.\Build.ps1
if (!(Test-Path 'Build')) {
    mkdir 'Build'
}
if ((Test-Path '.\Build\SCE-Latest.jar')) {
    Remove-Item .\Build\SCE-Latest.jar
}
jar -c  -f .\Build\SCE-Latest.jar  -C '.\bin\' .\org\