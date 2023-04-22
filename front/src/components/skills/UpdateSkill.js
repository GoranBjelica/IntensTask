import React from 'react';
import InteNSAxios from '../../apis/InteNSAxios';
import './../../index.css';
import { withParams, withNavigation } from '../../routeconf'
import { Button, Col, Form, Row } from 'react-bootstrap';

class UpdateSkill extends React.Component{

    constructor(props) {
        super(props);

        this.state = {
           candidateId: -1,
           candidateName: "",
           skills: [],
           skillId: "",
           skillTitle: "",
           candidate :{
            id: "",
            fullName: ""
           }
        }
    }
    componentDidMount(){
        this.getSkills(this.props.params.id);
        this.getCandidateById(this.props.params.id);
    }
    async getCandidateById(candidateId){
        
        InteNSAxios.get("/candidates/"+ candidateId)
        .then(res => {
            this.setState({candidate : res.data})
        })
        .catch(error=> console.log(error)
        )
    }

    async getSkills(candidateId){
        InteNSAxios.get("/skills/candidatedoesnthave/" + candidateId)
        .then(res => {
          this.setState({skills : res.data})
          console.log(this.state.skills)
        })
        .catch(error => console.log(error))
    }

    selectChange(e){
        const id = e.target.value;
        this.setState({skillId: id})
    }

addSkill(){
    let params = {
        "id": this.state.skillId,
        "title": this.state.skillTitle
    }
    InteNSAxios.put('/candidates/updateskill/'+ this.state.candidate.id, params)
    .then(res=> {
        window.location.reload();
    } 
    ).catch(error => alert("Error, skill not added"))
    
}

    render() {
        return (
            <>
            <Form style={{ width: "99%" }}>
                <Row>
                <Form.Group>
                    <Form.Label>Add new skill for candidate: {this.state.candidate.fullName}</Form.Label>
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
                <Button className="mt-3" onClick={() => this.addSkill()}>Add new skill </Button>
            </Col></Row>
            </>
        );
    }

}

export default withNavigation(withParams(UpdateSkill));