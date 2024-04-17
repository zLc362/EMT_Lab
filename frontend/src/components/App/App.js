import './App.css';
import EMTLabService from "../../repository/emtLabRepository";
import Countries from "../Countries/countries";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import React, {Component} from "react";
import Header from "../Header/header";
import Authors from "../Authors/authors";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";
import Books from "../Books/BookList/bookList";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      authors: [],
      books: [],
      countries: [],
      selectedBook: {}
    }
  }

  render() {
    return (
        <Router>
          <Header/>
          <main>
            <div className="container">
              <Route path={"/authors"} exact render={() =>
                  <Authors authors={this.state.authors}/>}/>
              <Route path={"/countries"} exact render={() =>
                  <Countries countries={this.state.countries}/>}/>
              <Route path={"/books/add"} exact render={() =>
                   <BookAdd countries={this.state.countries}
                               authors={this.state.authors}
                               onAddBook={this.addBook}/>}/>
               <Route path={"/books/edit/:id"} exact render={() =>
                   <BookEdit countries={this.state.countries}
                               authors={this.state.authors}
                               onEditBook={this.editBook}
                               book={this.state.selectedBook}/>}/>
               <Route path={"/books"} exact render={() =>
                  <Books books={this.state.books}
                            onDelete={this.deleteBook}
                            onEdit={this.getBook}/>}/>
              {/*<Route path={"/login"} exact render={() => <Login onLogin={this.fetchData}/>}/>*/}
              <Redirect to={"/books"}/>
            </div>
          </main>
        </Router>
    );
  }

  componentDidMount() {
    this.fetchData()
  }

  fetchData = () => {
    this.loadBooks();
    this.loadCountries();
    this.loadAuthors();
  }

  loadAuthors = () => {
    EMTLabService.fetchAuthors()
        .then((data) => {
          this.setState({
            authors: data.data
          })
        });
  }

  loadBooks = () => {
    EMTLabService.fetchBooks()
        .then((data) => {
          this.setState({
            books: data.data
          })
        });
  }

  loadCountries = () => {
    EMTLabService.fetchCountries()
        .then((data) => {
          this.setState({
            countries: data.data
          })
        });
  }

  deleteBook = (id) => {
    EMTLabService.deleteBook(id)
        .then(() => {
          this.loadBooks();
        });
  }

  addBook = (name, category, authorId, availableCopies) => {
    EMTLabService.addBook(name, category, authorId, availableCopies)
        .then(() => {
          this.loadBooks();
        });
  }

  getBook = (id) => {
    EMTLabService.getBook(id)
        .then((data) => {
          this.setState({
            selectedBook: data.data
          })
        })
  }

  editBook = (id, name, category, authorId, availableCopies) => {
    EMTLabService.editBook(id, name, category, authorId, availableCopies)
        .then(() => {
          this.loadBooks();
        });
  }
}

export default App;

