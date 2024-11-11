import React, { useState, useEffect } from 'react';
import { fetchQuizzes } from '../../Service/GameService';
import TypingEffect from 'react-typing-effect';

function Game() {
    const [quizzes, setQuizzes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [isTypingComplete, setIsTypingComplete] = useState(false);

    useEffect(() => {
        const loadQuizzes = async () => {
            try {
                const token = localStorage.getItem('token'); 
                if (token) {
                    const data = await fetchQuizzes(token); 
                    setQuizzes(data);
                } else {
                    console.error('Token não encontrado');
                }
            } catch (error) {
                console.error('Erro ao carregar quizzes:', error);
            } finally {
                setLoading(false);
            }
        };

        loadQuizzes();
    }, []);

    const handleTypingComplete = () => {
        setIsTypingComplete(true);
    };

    if (loading) return <p>Carregando quizzes...</p>;

    return (
        <div className="d-flex justify-content-center align-items-center min-vh-100">
            <div className="container text-center">
                <h2>
                    <TypingEffect 
                        text={"Game time..."} 
                        speed={150} 
                        eraseDelay={10500} 
                        typingDelay={100}
                        cursor={!isTypingComplete}
                        onTypingDone={handleTypingComplete}
                    />
                </h2>
                {quizzes.length === 0 ? (
                    <p>Nenhum quiz encontrado.</p>
                ) : (
                    <div className="row justify-content-center">
                        {quizzes.map(quiz => (
                            <div
                                key={quiz.quizId}
                                className="col-md-4 mb-4"
                            >
                                <div className="card h-100">
                                    <div className="card-body">
                                        <h3 className="card-title">{quiz.quizTitle}</h3>
                                        <p className="card-text">{quiz.quizDescription}</p>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
}

export default Game;