  var APP_EB_URL = 'http://localhost:8080/eventbus';

  var data = {
	"questions": [
		{"id": 1, "txt":"質問1：好きなフルーツ"},
		{"id": 2, "txt":"質問2：好きなスポーツ"},
		{"id": 3, "txt":"質問3：好きなクラウドサービス"}
	],
	"answer1": {
		"vote1": "りんご",
		"vote2": "みかん",
		"vote3": "バナナ",
		"vote4": "マンゴー"
	},
	"answer2": {
		"vote1": "サッカー",
		"vote2": "野球",
		"vote3": "剣道",
		"vote4": "ダイビング"
	},
	"answer3": {
		"vote1": "AWS",
		"vote2": "さくらインターネット",
		"vote3": "ニフティクラウド",
		"vote4": "DegitalOcean"
	},
  };

  var ADDR_Q = 'questions';
  var ADDR_VOTE = 'vote';
  var ADDR_RESULT = 'result';
  var ADDR_RESET = 'reset';
