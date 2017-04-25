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
//test case: { \"name\":{\"first\":\"sam\", \"last\":\"doe\" }, \"name\":\"sam doe\" , \"name\":{\"first\":\"sam\", \"last\":\"doe\" } }
  @Override
  public JSON parse(String in) throws IOException {
    // TODO: implement this
	  JSON temp = new MyJSON();
	  String data = in.substring(in.indexOf("{")+1, in.lastIndexOf("}")).trim();
	  if(data.indexOf(":")>=0){
		  //out.println("1"+data);
		  int begin = 0;
		  int separatein = 0;
		  while(separatein<data.length() && data.indexOf(",", separatein)>0){
			  //out.println(begin+" "+separatein);
			  if(data.charAt(separatein)==','){
				 // out.println(begin+" " +separatein);
				  //evaluate as key-pair: data.substring(begin, separatein).trim()
				  String keypair = data.substring(begin, separatein).trim();
				  //out.println(keypair);
				  int colon_index = keypair.indexOf(":");
				  if(keypair.substring(colon_index+1).trim().indexOf("{")==0){
					  String key = keypair.substring(0, colon_index).trim();
					  String value = keypair.substring(colon_index+1).trim();
					  temp.setObject(key.substring(1, key.length()-1), parse(value));
				  }else{
					  String key = keypair.substring(0, colon_index).trim();
					  String value = keypair.substring(colon_index+1).trim();
					  temp.setString(key.substring(1,key.length()-1), value.substring(1,value.length()-1));
				  }
				  begin = separatein+1;
			  }
			  if(data.charAt(separatein)=='{'){
				  separatein = indexOfPair(data, separatein);
				  //out.println("skip to "+separatein);
			  }
			  separatein++;
		  }
		  //evaluate as key-pair: data.substring(begin).trim()
		  String keypair = data.substring(begin).trim();
		 // out.println("end"+keypair);
		  int colon_index = keypair.indexOf(":");
		  if(keypair.substring(colon_index+1).trim().indexOf("{")==0){
			  String key = keypair.substring(0, colon_index).trim();
			  String value = keypair.substring(colon_index+1).trim();
			  temp.setObject(key.substring(1, key.length()-1), parse(value));
		  }else{
			  String key = keypair.substring(0, colon_index).trim();
			  String value = keypair.substring(colon_index+1).trim();
			  temp.setString(key.substring(1,key.length()-1), value.substring(1,value.length()-1));
		  }
	  }
	return temp;
  }
   public int indexOfPair(String in, int start){
	  int tozero = 1;
	  int i;
	  for( i = start+1; i<in.length(); i++){
		  if(in.charAt(i)=='{')
			  tozero++;
		  if(in.charAt(i)=='}')
			  tozero--;
		  if(tozero == 0)
			  break;
	  }
	  return i;
  }

}
