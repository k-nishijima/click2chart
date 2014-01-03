## What is click2chart?

某氏のブログを読んでSmartClickなるプロダクトを知り、へ〜と思って正月休みなのでちゃちゃっと作ってみました。設問と選択肢を設定し、WebSocketでリアルタイムに結果がグラフ表示されます。勉強会などで使えるかもしれません。
また、vert.x を使った WebSocket のサンプルとしても使えるかもしれません。

## Dependencies

* modern web browsers
* ccchart http://ccchart.com/
* vert.x http://vertx.io/

あなたは Groovy + vert.x してますか？何、知らない？ふーむ、残念です。では以下でインストールして下さい。詳しくは http://gvmtool.net/ へどうぞ。

    $ curl -s api.gvmtool.net | bash
	$ gvm install vertx

## Configuring

data.jsファイルを編集し、設問や回答を設定します。

### var APP_EB_URL
* デプロイしたURLを設定します。

### var data
* questions に質問を設定します。idがプライマリキー、txtに本文を書いて下さい。
* answer + id（プライマリキー）に、回答を設定します。vote1 から vote4 は連番で記述し、もし4つ以上の選択肢を増やしたい場合は vote.html にもボタンを増やして下さい。
* 必ず設問の数とanswerの数が一致するようにして下さい。


## Running the Server

    $ vertx run Click2ChartServer.groovy

* 投票画面 http://localhost:8080/
* 回答結果画面 http://localhost:8080/view

回答画面で設問を選択してから、投票してみてください。リアルタイムにグラフが描画されると思います。

【Connection Status が Connected の状態で、ブラウザとサーバが接続完了した状態になります。それ以外のステータスでは、動作しないのでご注意下さい。】

## 既知の問題

* スマートフォンからは投票はできますが、回答結果は見れないと思います。多分。
* 回答結果はどこにも保存されません。サーバを止めた時点で消えてなくなります。
* 回答結果画面は、設問を選択したあと、誰かが解答を投票しないと結果が画面に反映されません
* あまりまじめに排他していないので、ボタン連打をすると集計が狂う可能性があります。
* あまりまじめに（略）、変なデータを投げると壊れるので気を付けて下さい。


## 画面をCSSで綺麗にしたり、データ永続化/後日ダウンロード機能などのプルリクお待ちしております(^^)

contact to [@k_nishijima](https://twitter.com/k_nishijima)
