import axios from 'axios';

var InteNSAxios = axios.create({
  baseURL: 'http://localhost:8080/api',
  /* other custom settings */
});


export default InteNSAxios;
