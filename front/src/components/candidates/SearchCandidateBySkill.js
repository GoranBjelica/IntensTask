import React from 'react';
import InteNSAxios from '../../apis/InteNSAxios';
import { Row, Col, Button, Table, Form } from 'react-bootstrap'
import './../../index.css';
import {  withNavigation } from '../../routeconf'

class SearchCandidateBySkill extends React.Component{
    constructor(props){
        super(props);
        this.state = {candidates: [], skillId: "", skills: []}
    }


    componentDidMount(){
        this.getSkills();
    }
    async getSkills(){
        InteNSAxios.get('/skills')
        .then(res =>{
            this.setState({skills:res.data})
        })
        .catch(error => console.log(error))
    }

    getCandidates(){
        InteNSAxios.get('/skills/' + this.state.skillId)
        .then(res =>{
            this.setState({candidates:res.data})
        })
        .catch(error => alert('Error!'));
    }
    
    getSkillsFromCandidate(cand){
        return cand.map(e =>e.title).join(', ');
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
                  <td><Button variant="primary" onClick={() => this.goToUpdateSkill(cand.id)}>Add new skill</Button></td>
                  <td><Button variant="warning" onClick={() => this.goToDeleteSkill(cand.id)}>Remove skill</Button></td>
                  </tr>
            )
         })
    }
    
    selectChange(e){
        const id = e.target.value;
        this.setState({skillId: id})
    }

    render() {
        return (
            <>
            <Form style={{ width: "99%" }}>
            <Row>
                <Form.Group>
                    <Form.Label>Search candidate by skill</Form.Label>
                           <Form.Select name="skillId" onChange={event => this.selectChange(event)}>
                                    <option value= "">-------</option>
                                    {
                                        this.state.skills.map((s) => {
                                            return (
                                                <option key={s.id} value={s.id} >{s.title} </option>
                                            )
                                        })
                                    }
                             </Form.Select><br />
                 </Form.Group>
                </Row>       
            </Form>
            <Row><Col>
                <Button className="mt-3" onClick={() => this.getCandidates()}>Search</Button>
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

export default withNavigation(SearchCandidateBySkill)