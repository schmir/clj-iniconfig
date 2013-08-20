# iniconfig

A minimal Clojure library designed to read .ini files compatible with
python's py.iniconfig, i.e. it uses the number sign '#' as comment
character and allows line continuations.

## Installation

iniconfig artifacts are
[released to Clojars](https://clojars.org/com.brainbot/iniconfig).

If you are using [Leiningen](http://leiningen.org), add the following
to your `project.clj`'s dependencies:

``` clj
	[com.brainbot/iniconfig "0.2.0"]
```

## Usage

iniconfig has only one namespace `com.brainbot.iniconfig` with two
public functions:

``` clj
=> (require '[com.brainbot.iniconfig :as iniconfig])
```

`read-ini [in]` will read an ini file:

``` clj
=> (spit "test.ini" "[main]\nmsg = foo\n  bar\n[email]\nfrom = ralf@systemexit.de")
=> (iniconfig/read-ini "test.ini")
{"email" {"from" "ralf@systemexit.de"}, "main" {"msg" "foo\n  bar"}}
```

`read-ini` tries to coerce it's `in` argument with
`clojure.java.io/reader`. Please consult the `io/reader` documentaton
for further details.

As an example it's also possible to load an ini file via HTTP:

``` clj
=> (iniconfig/read-ini "https://raw.github.com/brainbot-com/clj-iniconfig/master/example.ini")
{"pytest" {"norecursedirs" "bin parts develop-eggs eggs .* _* CVS {args}"}, "testenv:py25" {"deps" "pytest>=2.3\n     WebTest==1.4.3\n     WebOb==0.9.6.1\n     BeautifulSoup==3.2.1"}, "testenv" {"sitepackages" "False", "commands" "py.test []", "deps" "pytest>=2.3\n     webtest\n     beautifulsoup4"}, "tox" {"envlist" "py25,py26,py27,py32,py33"}}
```

`read-ini-string` will parse an ini file supplied as string:

``` clj
=> (iniconfig/read-ini-string "[main]\nmsg = foo\n  bar\n[email]\nfrom = ralf@systemexit.de")
{"email" {"from" "ralf@systemexit.de"}, "main" {"msg" "foo\n  bar"}}
```

Both functions set the metadata field `:source`, which might be useful
for error reporting:

``` clj
=> (meta (iniconfig/read-ini "test.ini"))
{:source "test.ini"}

=> (meta (iniconfig/read-ini-string "[main]\nmsg = foo\n  bar\n[email]\nfrom = ralf@systemexit.de"))
{:source string}
```

## ini file syntax

iniconfig uses the following syntax for ini files:

* The number sign `#` is used as a comment character. Lines starting
  with `#` or whitespace followed by `#` are interpreted as comments.

* Lines starting with `[`, followed by an identifier, followed by `]`
  start a new section.

* Lines starting with an identifier, followed by an equal sign `=`
  define a key with it's accompanying value.

* Lines starting with whitespace, followed by a non-whitespace
  character that is not the number sign `#`, continue the previous
  value declaration, i.e. they can be used for multi-line values.


## License

Copyright © 2013 brainbot technologies AG

Distributed under the Eclipse Public License, the same as Clojure.
