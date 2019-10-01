(defproject com.brainbot/iniconfig "0.2.0"
  :description "minimal .ini file parser"
  :url "https://github.com/brainbot-com/clj-iniconfig"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url https://github.com/brainbot-com/clj-iniconfig}
  :profiles {:1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}
             :1.9 {:dependencies [[org.clojure/clojure "1.9.0"]]}
             :1.10.0 {:dependencies [[org.clojure/clojure "1.10.0"]]}
             :1.10.1 {:dependencies [[org.clojure/clojure "1.10.1"]]}}


  :aliases {"test-all" ["with-profile" "+1.8:+1.9:+1.10.0:+1.10.1" "test"]
            "deps-all" ["with-profile" "+1.8:+1.9:+1.10.0:+1.10.1" "deps"]}

  :deploy-repositories [["releases" :clojars]
                        ["snapshots" :clojars]]
  :dependencies [[org.clojure/clojure "1.10.1"]])
