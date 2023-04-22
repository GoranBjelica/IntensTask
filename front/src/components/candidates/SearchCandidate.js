import React from 'react';
import InteNSAxios from '../../apis/InteNSAxios';
import { Row, Col, Button, Table, Form } from 'react-bootstrap'
import './../../index.css';
import { withNavigation } from '../../routeconf'

class SearchCandidate extends React.Component {

    constructor(props) {
        super(props);

        
        let candidate = {
            id: "",
            fullName: "",
            eMail: "",
            contactNumber: "",
            dateOfBirth: "",
            skills: []
             
          }

        this.state = {candidates: [], candidateForSearch: "", candidate: candidate }
    }

    getCandidate() {       
        const config ={
            params:{
                fullName: this.state.candidateForSearch
            }
        }
      
        InteNSAxios.get('/candidates/', config)  
            .then(res => {      
                console.log(res.data)         
                this.setState({
                    candidates:res.data
                    
                });
            })
            .catch(error => { console.log(error)
            });
    }

    deleteCandidate(id) {
        InteNSAxios.delete('/candidates/' + id)
            .then(res => {
                console.log(res);
                this.getCandidate();

                        })
            .catch(error => {
                console.log(error);
                alert('Candidate not removed');
            });
    }

    onInputChange(e) {
        
       let input = e.target.value;
       this.setState({candidateForSearch: input})
    }

    getSkillsFromCandidate(cand){
        return cand.map(e =>e.title).join(',');
    }
    goToUpdateSkill(candidateId){
    
        this.props.navigate("/updateSkill/" + candidateId);
      }

    goToDeleteSkill(candidateId){
        this.props.navigate("/deleteSkill/" + candidateId)
    }  

    renderCandidate() {

        return this.state.candidates.map((cand, index) => {
            return (
               <tr key={cand.id}>
                  <td>{cand.fullName}</td>
                  <td>{cand.eMail}</td>
                  <td>{cand.contactNumber}</td>
                  <td>{cand.dateOfBirth}</td>
                  <td>{this.getSkillsFromCandidate(cand.skills)}</td>
                  <td><Button variant="danger" onClick={() => this.deleteCandidate(cand.id)}>Delete candidate</Button></td>
                  <td><Button variant="primary" onClick={() => this.goToUpdateSkill(cand.id)}>Add new skill</Button></td>
                  <td><Button variant="warning" onClick={() => this.goToDeleteSkill(cand.id)}>Remove skill</Button></td>
                  </tr>
            )
         })
    }

    render() {
        return (
            <>
            <Form style={{ width: "99%" }}>
                <Row>
                <Form.Group>
                    <Form.Label htmlFor="name">Enter candidate full name</Form.Label>
                         <Form.Control
                          placeholder="Enter full name"
                          name="fullName"
                          type="text"
                          onChange={(e) => this.onInputChange(e)}
                         />
               </Form.Group>
                </Row>
                               
            </Form>
            <Row><Col>
                <Button className="mt-3" onClick={() => this.getCandidate()}>Search</Button>
            </Col>
            </Row>
            <Row>
            <Table style={{marginTop:5}}>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>E mail</th>
                                <th>Contact number</th>
                                <th>Date of birth</th>
                                <th>Skills</th>
                               
                            </tr>
                        </thead>
                        <tbody>
                        {this.renderCandidate()}
                        </tbody>                  
                    </Table>

           
            </Row>
            </>
        );
    }
   
}

export default withNavigation(SearchCandidate);