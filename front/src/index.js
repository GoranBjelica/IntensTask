import React from 'react';
import ReactDOM from 'react-dom';
import { Route, Link, HashRouter as Router, Routes } from 'react-router-dom';
import { Navbar, Nav, Container} from 'react-bootstrap';
import Home from './components/Home';
import NotFound from './components/NotFound';
import AddCandidate from './components/candidates/AddCandidate';
import AddSkill from './components/skills/AddSkill';
import SearchCandidate from './components/candidates/SearchCandidate';
import SearchCandidateBySkill from './components/candidates/SearchCandidateBySkill';
import UpdateSkill from './components/skills/UpdateSkill';
import DeleteSkill from './components/skills/DeleteSkill';



class App extends React.Component {

    render() {
               
            return (
            <>
                <Router>
                    <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                            Intens
                        </Navbar.Brand>
                        <Nav>
                        <Nav.Link as={Link} to="/searchCandidate">
                            Search candidate by name
                        </Nav.Link>
                        <Nav.Link as={Link} to="/searchBySkill">
                            Search candidate by skill
                        </Nav.Link>
                        <Nav.Link as={Link} to="/addCandidate">
                            Add Candidate
                        </Nav.Link>
                        <Nav.Link as={Link} to="/addSkill">
                            Add new skill
                        </Nav.Link>
                        
                        </Nav>
                    </Navbar>
                    <Container style={{paddingTop:"10px"}}>
                    <Routes>
                        <Route path="/" element={<Home/>} />
                        <Route path="/searchCandidate" element={<SearchCandidate/>} />
                        <Route path="/searchBySkill" element={<SearchCandidateBySkill/>} />
                        <Route path="/addCandidate" element={<AddCandidate/>} />
                        <Route path="/addSkill" element={<AddSkill/>} />
                        <Route path='/updateSkill/:id' element={<UpdateSkill/>}></Route>
                        <Route path='/deleteSkill/:id' element={<DeleteSkill/>}></Route>
                        <Route path="*" element={<NotFound/>} />
                    </Routes>
                </Container>
                </Router>
            </>
        );      
    }
};


ReactDOM.render(
    <App/>,
    document.querySelector('#root')
);
