/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

def eb = vertx.eventBus

eb.registerHandler("vote") { message -> 
  def resultKey = 'result' + message.body.q
  def map = vertx.sharedData.getMap(resultKey)

  def voteKey = "vote"+ message.body.vote
  if (map.containsKey(voteKey)) { 
	map[voteKey]++
  } else { 
	map[voteKey] = 1
  }

  def json = new groovy.json.JsonBuilder()
  json {
	map.collect { i ->
	  "${i.key}" "${i.value}"
	}
  }

  println json.toString()
  eb.send(resultKey, json.toString())
}

eb.registerHandler("reset") { message -> 
  def resultKey = 'result' + message.body.q
  def map = vertx.sharedData.getMap(resultKey)
  map.clear()
  eb.send(resultKey, ["reset":"empty"])
  println "reset "+ resultKey
}


def server = vertx.createHttpServer()

server.requestHandler { req ->
  if (req.uri == '/') req.response.sendFile('vote.html')
  if (req.uri == '/view') req.response.sendFile('viewer.html')
  if (req.uri == '/ccchart.js') req.response.sendFile('ccchart.js')
  if (req.uri == '/data.js') req.response.sendFile('data.js')
  if (req.uri == '/vertxbus.js') req.response.sendFile('vertxbus.js')
}

vertx.createSockJSServer(server).bridge(prefix: '/eventbus', [[:]], [[:]])

server.listen(8080)
println "SockJSServer startup."
