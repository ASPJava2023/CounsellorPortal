# verify you're on main and origin is correct
git rev-parse --abbrev-ref HEAD
git remote get-url origin

# run the push script (PowerShell)
.\push-to-remote.ps1

# verify no local changes and no commits ahead of origin/main
git status --porcelain
git log --oneline origin/main..main# verify you're on main and origin is correct
git rev-parse --abbrev-ref HEAD
git remote get-url origin

# run the push script (PowerShell)
.\push-to-remote.ps1

# verify no local changes and no commits ahead of origin/main
git status --porcelain
git log --oneline origin/main..main# verify you're on main and origin is correct
git rev-parse --abbrev-ref HEAD
git remote get-url origin

# run the push script (PowerShell)
.\push-to-remote.ps1

# verify no local changes and no commits ahead of origin/main
git status --porcelain
git log --oneline origin/main..mainparam(
    [string]$RemoteName = "origin",
    [string]$RemoteUrl = "",
    [string]$CommitMessage = "chore: commit all changes before push"
)

# ...existing code...
# quick checks
if (-not (Get-Command git -ErrorAction SilentlyContinue)) {
    Write-Error "git not found in PATH."
    exit 1
}

$inside = git rev-parse --is-inside-work-tree 2>$null
if ($LASTEXITCODE -ne 0) {
    Write-Error "Not a git repository. Run this script inside your repo root."
    exit 1
}

# stage everything
git add -A

# commit if there are changes
$status = git status --porcelain
if ($status) {
    git commit -m $CommitMessage
} else {
    Write-Output "No changes to commit."
}

# ensure remote exists or add it if -RemoteUrl provided
$remoteUrlExisting = $null
try { $remoteUrlExisting = git remote get-url $RemoteName 2>$null } catch {}
if (-not $remoteUrlExisting) {
    if (-not $RemoteUrl) {
        Write-Error "Remote '$RemoteName' not found. Provide -RemoteUrl to add it, e.g. -RemoteUrl 'git@github.com:owner/repo.git'"
        exit 1
    }
    git remote add $RemoteName $RemoteUrl
    Write-Output "Added remote '$RemoteName' -> $RemoteUrl"
} else {
    Write-Output "Remote '$RemoteName' exists: $remoteUrlExisting"
}

# determine current branch
$branch = git symbolic-ref --short HEAD 2>$null
if (-not $branch) {
    Write-Output "Could not detect current branch; defaulting to 'main'"
    $branch = "main"
}

# push
git push -u $RemoteName $branch

Write-Output "Push complete."
# ...existing code...

