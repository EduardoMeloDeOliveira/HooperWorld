import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

export const fetchQuizzes = async (token) => {

    console.log(token)
    try {
        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };

        const response = await axios.get(`${API_BASE_URL}/quizzes`, config);
        return response.data;
    } catch (error) {
        console.error('Erro ao buscar quizzes:', error);
        throw error;
    }
};
