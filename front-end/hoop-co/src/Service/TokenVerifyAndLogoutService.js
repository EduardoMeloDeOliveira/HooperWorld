function verifyToken() {
    const token = localStorage.getItem("token");
    return token ? true : false;
}

function logoutService(navigate) {
    if (!verifyToken()) {
        navigate("/");
    } else {
        localStorage.removeItem("token");
        navigate("/");
    }
}

export { verifyToken, logoutService };
