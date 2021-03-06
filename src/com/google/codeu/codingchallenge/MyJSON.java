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

import java.util.Collection;
import java.util.HashMap;

import static java.lang.System.*;

final class MyJSON implements JSON {
  private static HashMap<String, Object> JSONs=new HashMap<>();
  @Override
  public JSON getObject(String name) {
    // TODO: implement this
    if(JSONs.get(name) instanceof JSON)
      return (JSON)JSONs.get(name);
    return null;
  }

  @Override
  public JSON setObject(String name, JSON value) {
    // TODO: implement this
    JSONs.put(name, value);
    return this;
  }

  @Override
  public String getString(String name) {
    // TODO: implement this
    if(JSONs.get(name) instanceof String)
      return (String)JSONs.get(name);
    return null;
  }

  @Override
  public JSON setString(String name, String value) {
    // TODO: implement this
    JSONs.put(name, value);
    return this;
  }

  @Override
  public void getObjects(Collection<String> names) {
    // TODO: implement this
    for(String n:JSONs.keySet()){
      if(JSONs.get(n) instanceof JSON && ((JSON)JSONs.get(n)).getObject(n)!=null)
        names.add(n);
    }
  }

  @Override
  public void getStrings(Collection<String> names) {
    // TODO: implement this
    for(String n:JSONs.keySet()){
      if(JSONs.get(n) instanceof JSON && ((JSON)JSONs.get(n)).getString(n)!=null)
      names.add(n);
    }
  }
}
