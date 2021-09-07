Write-Output "[AB]Stardust Crusader Engine Essential Asset Bundle Create Script"
Write-Output "[AB]Creating Base Resource Bundle..."
java -jar .\Build\Tools\AssetBundleTool.jar Assets\Resources\ .\Build\BaseResources.bundle -q
Write-Output "[AB]Done."