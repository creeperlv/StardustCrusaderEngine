Write-Output '[BuildTool]Stardust Crusader Engine Tools Build Script'
Write-Output '[BuildTool]Start Build from source...'
.\Build.ps1
Write-Output '[BuildTool]Generating Tools...'
if (!(Test-Path 'Build')) {
    mkdir 'Build'
}
if (!(Test-Path 'Build\Tools')) {
    mkdir 'Build\Tools'
}
jar -c  -f .\Build\Tools\AssetBundleTool.jar -e 'org.StardustCrusader.GameEngine.Tools.AssetBundleTool' -C '.\bin\' .\org\
jar -c  -f .\Build\Tools\AssetBundleManifestTool.jar -e 'org.StardustCrusader.GameEngine.Tools.AssetBundleManifestTool' -C '.\bin\' .\org\
Write-Output '[BuildTool]Done.'