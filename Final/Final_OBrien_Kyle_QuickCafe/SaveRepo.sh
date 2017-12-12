# I've been using a private Github repository to save my work and revert to previous commits when I've broken my code.
# This script runs the commands necessary to save my changes, make a commit, and push the changes to the remote repo.

git status
git add .
git commit -m "save progress"
git push