# Learn Yourself a New Language ([LYANL](https://nugit.github.io/LYANL/))

## Setting up GitHub Pages with Jerkyll locally
(More information can be found [here](https://help.github.com/articles/setting-up-your-github-pages-site-locally-with-jekyll/))
1. Open Terminal
2. Check whether you have Ruby 2.1.0 or higher installed:
```
$ ruby --version
ruby 2.X.X
```
3. If you don't have Ruby installed, install [Ruby 2.1.0 or higher](https://www.ruby-lang.org/en/downloads/).
4. Install Bundler:
```
$ gem install bundler
```
5. Install Jekyll and other dependencies from the GitHub Pages gem:
```
$ bundle install
```
6. In the root directory of your local Jekyll site repository, run
```
bundle exec jekyll serve
```
7. Preview your local Jekyll site in your web browser at `http://localhost:4000`.
