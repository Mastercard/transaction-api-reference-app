## Contributing

## Submitting Changes

### For Mastercard employees

1. git clone git@github.com:Mastercard/transaction-api-reference-app.git
2. git add remote upstream git@github.com:Mastercard/transaction-api-reference-app.git (hook up the upstream repo with your local copy)
3. git fetch --all
4. git checkout main
5. git merge upstream main (to get the latest version of the upstream's code, do this daily)
6. git checkout -b "feature/foobranch"
7. Commit your changes (git commit -am 'Add some feature')
   - Don't forget to update existing tests or add new tests
8. Squash your commits (git rebase --interactive --autosquash)
9. Execute the unit tests to ensure all pass
10. git push origin feature/foobranch

### For everyone else

1. Fork the repository
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
    - Don't forget to update existing tests or add new tests
4. Execute the unit tests to ensure all pass
5. Squash your commits (git rebase --interactive --autosquash)
6. Push the branch (git push origin my-new-feature)
7. Create a new Pull Request

## Quality Expectations

Please ensure any contributions include unit tests. The project maintains a high level of test coverage for its functionality. Submissions are expected to maintain a similar level of coverage.