// Copyright 2020 Google LLC
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
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

/**
 * Fetches a list of messages, choses 1 randomly, and displays it on the page 
 */
let response = null;
let responseContent = null;
async function getSubmissionMessage()
{ 
  if(response == null)
  {
    response = await fetch("/random-message");
    responseContent = await response.json();
    console.log(responseContent);
  }
  const message = responseContent[Math.floor(Math.random() * responseContent.length)];
  console.log(message);

  const submissionPrompt = document.getElementById("random-message-container");
  submissionPrompt.innerText = message;
} 
