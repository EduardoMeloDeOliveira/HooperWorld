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


async function createPost(token, postData) {
  const response = await axios.post(`${API_BASE_URL}/posts`, postData, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}


async function fetchUserIdFromToken(token) {
  const response = await axios.get(`${API_BASE_URL}/users/user-id`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}


async function fetchTopTenPosts(token) {
  const response = await axios.get(`${API_BASE_URL}/posts/get-top-ten`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}

async function deletePost(token, postId) {
  const response = await axios.delete(`${API_BASE_URL}/posts/${postId}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response;
}


async function updatePost(token, postId, postData) {
  const response = await axios.put(`${API_BASE_URL}/posts/${postId}`, postData, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}


async function toggleLike(token, postId) {
  const response = await axios.post(`${API_BASE_URL}/likes/${postId}`, null, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
}

const uploadUserProfileImage = async (formData, token) => {
  console.log(token)
  const response = await axios.post(`${API_BASE_URL}/users/upload-image`, formData, {
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'multipart/form-data',
    },
  });
  return response.data;
};


const getUserImage = async (token) => {
  try {
    const response = await axios.get(`${API_URL}/users/image`, {
      headers: {
        'Authorization': token, 
      },
      responseType: 'blob',
    });
    
    if (response.status === 200) {
      const imageUrl = URL.createObjectURL(response.data); 
      return imageUrl;
    } else {
      console.error('Erro ao obter a imagem do usuário');
      return null;
    }
  } catch (error) {
    console.error('Erro na requisição para obter a imagem:', error);
    return null;
  }
};

export {
  registerUser,
  loginUser,
  fetchUserProfile,
  fetchPostsByUserId,
  createPost,
  fetchUserIdFromToken,
  fetchTopTenPosts,
  deletePost,
  updatePost,
  toggleLike,
  uploadUserProfileImage,
  getUserImage
};
