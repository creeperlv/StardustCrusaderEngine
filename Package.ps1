Write-Output '[Package]Stardust Crusader Engine Pack Script'
Write-Output '[Package]Starting a build...'
.\Build.ps1
if (!(Test-Path 'Build')) {
    mkdir 'Build'
}
if ((Test-Path '.\Build\SCE-Latest.jar')) {
    Remove-Item .\Build\SCE-Latest.jar
}
Write-Output '[Package]Packaging with JAR'
jar -c  -f .\Build\SCE-Latest.jar  -C '.\bin\' .\org\
Write-Output '[Package]Done.'
$NAME= Get-ChildItem ".\Build\SCE-Latest.jar" | Select-Object FullName
Write-Output "[Package]Outcome: $NAME"