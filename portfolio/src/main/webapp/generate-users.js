function createUsers(){
  const emailSuffixes = ["@nyu.edu", "@pitt.edu", "@psu.edu"];
  const names = ["Amy", "Linda", "James"];

  //

  for(let i = 0; i < 10; i++)
  {
    const newUser = names[Math.floor(Math.random() * names.length)] + (Date.now() + i) + emailSuffixes[Math.floor(Math.random() * emailSuffixes.length)];
    console.log(newUser);
  }
}