import axios from '../custom-axios/axios';

const EMTLabService = {
    fetchBooks: () => {
        return axios.get("/books");
    },
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchCountries: () => {
        return axios.get("/countries");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name, category, authorId, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "category": category,
            "authorId": parseInt(authorId),
            "availableCopies": parseInt(availableCopies)
        });
    },
    editBook: (id, name, category, authorId, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "authorId": parseInt(authorId),
            "availableCopies": parseInt(availableCopies)
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    // login: (username, password) => {
    //     return axios.post("/login", {
    //         "username": username,
    //         "password": password
    //     });
    // },
}

export default EMTLabService;
