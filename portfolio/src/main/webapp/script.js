// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random fact to the page.
 */
function addRandomFact() {
  const facts =
    ['I am a virgo ♍ ', 'I have been on Television before 📺 ' , 'I love the 90s show Golden Girls 👵👵👵 ']; 

  // Pick a random fact.
  const fact = facts[Math.floor(Math.random() * facts.length)];

  // Add it to the page.
  const factContainer = document.getElementById('fact-container');
  factContainer.innerText = fact;
}

/**
 * Fetch the list of comments and add them to the page
 */
function fetchAndAddComments(){
  const maxComments = parseInt(document.getElementById('max-comments').value);
  fetch( '/comments?max-comments=' + maxComments )
  .then(response => response.json())
  .then((comments) =>{
    const commentContainer = document.getElementById('comments-container');
    commentContainer.innerHTML = "";
    comments.forEach(function(comment) {
      commentContainer.appendChild(createCommentElement(comment));
    });
  });
}

/** 
 * Creates a <li> element containing the comment.
 */
function createCommentElement(comment) { 
  const commentElement = document.createElement('li');
  commentElement.appendChild(document.createTextNode(comment['username']+ "\n"));
  commentElement.appendChild(document.createTextNode(comment['comment'] + "\n"));
  return commentElement;
}