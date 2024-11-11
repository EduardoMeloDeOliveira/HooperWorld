import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

async function registerUser(user) {
  const response = await axios.post(`${API_BASE_URL}/auth/register`, user);
  return response;
}

async function loginUser(user) {
  const response = await axios.post(`${API_BASE_URL}/auth/login`, user);
  return response;
}

async function fetchUserProfile(token) {
  const response = await axios.get(`${API_BASE_URL}/users`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}

async function fetchPostsByUserId(token) {
  const response = await axios.get(`${API_BASE_URL}/posts/get-post-by-user`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}

export { registerUser, loginUser, fetchUserProfile, fetchPostsByUserId };
