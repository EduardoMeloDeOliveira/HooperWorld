import axios from 'axios';

async function registerUser(user) {
  const response = await axios.post('http://localhost:8080/auth/register', user);
  return response;
}

async function loginUser(user){
    const response = await axios.post('http://localhost:8080/auth/login',user)
    return response
}

export {registerUser,loginUser}