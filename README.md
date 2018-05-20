# sweaty-timer

This is a special kind of timer.
It will make you healthy.
It will make you strong.
It will make you realize that you are great. Anyone who disagrees is an asshole.

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build


To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```
