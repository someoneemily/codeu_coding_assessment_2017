// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.codingchallenge;

import java.io.IOException;
import java.util.Arrays;

import static java.lang.System.*;


final class MyJSONParser implements JSONParser {
//{ \"name\":{\"first\":\"sam\", \"last\":\"doe\" } }
// \"name\"    :    {\"first\":\"sam\", \"last\":\"doe\" }
  @Override
  public JSON parse(String in) throws IOException {
    // TODO: implement this
    JSON temp = new MyJSON();
	String data = in.substring(in.indexOf("{")+1, in.lastIndexOf("}")).trim();
	if(data.indexOf(":")>=0){
		if(data.substring(data.indexOf(":")+1).trim().indexOf("{")==0){
			String key = data.substring(0, data.indexOf(":")).trim();
			String value = data.substring(data.indexOf(":")+1).trim();
			temp.setObject(key.substring(1, key.length()-1), parse(value));
		}else if(data.indexOf(",")>0){
			JSON temp1 = new MyJSON();
			String[] ar = data.split(",");
			for(String st:ar){
				parse("{"+st+"}");
			}
		}
		else{
			String key = data.substring(0, data.indexOf(":")).trim();
			String value = data.substring(data.indexOf(":")+1).trim();
			temp.setString(key.substring(1,key.length()-1), value.substring(1,value.length()-1));
		}
	}
    return temp;
  }
}
