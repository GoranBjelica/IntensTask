import React from "react";
import { Form, Button, Row, Col } from "react-bootstrap";
import InteNSAxios from "../../apis/InteNSAxios";
import { withNavigation } from "../../routeconf";

class AddCandidate extends React.Component {
  constructor(props) {
    super(props);

    let candidate = {
      fullName: "",
      eMail: "",
      contactNumber: "",
      dateOfBirth: "",
      skills: []
       
    }
    let skill = {
      "id": "",
      "title": ""
    }

    this.state = {candidate: candidate, allSkills: [], skill: skill };
    this.create = this.create.bind(this);
  }
  componentDidMount(){
    
    this.getSkills();
  }

  async getSkills(){
    InteNSAxios.get("/skills")
    .then(res => {
      this.setState({allSkills : res.data})
      console.log(this.state.allSkills)
    })
    .catch(error => console.log(error))
  }

  async create() {
  
   await  InteNSAxios.post("/candidates", this.state.candidate)
      .then((res) => {
        console.log(res);
        
        let candidateId = res.data.id;
        this.goToUpdateSkill(candidateId);
        })
      
      .catch((error) => {
        console.log(error);
        alert(error)      
      });
  }
  goToUpdateSkill(candidateId){
    
    this.props.navigate("/updateSkill/" + candidateId);
  }
  
  valueInputChanged(e) {
    let input = e.target;

    let name = input.name;
    let value = input.value;

    let candidate = this.state.candidate;
    candidate[name]= value;
    
    this.setState({candidate}); 
}

  render() {
    return (
      <>
        <Row>
          <Col></Col>
          <Col xs="12" sm="10" md="8">
            <h1 style={{color: "purple"}}>Add new candidate</h1>
            <Form>

              <Form.Label htmlFor="name">Full name</Form.Label>
              <Form.Control
                placeholder="Full name"
                name="fullName"
                type="text"
                onChange={(e) => this.valueInputChanged(e)}
              />

              <Form.Label htmlFor="eMail">Email</Form.Label>
              <Form.Control
                placeholder="E mail"
                name="eMail"
                type="text"
                onChange={(e) => this.valueInputChanged(e)}
              />

              <Form.Label htmlFor="contactNumber">Contact number</Form.Label>
              <Form.Control
                placeholder="Contact number"
                name="contactNumber"
                type="text"
                onChange={(e) => this.valueInputChanged(e)}
              />

            <Form.Label htmlFor="date">Date of birth</Form.Label>
              <Form.Control
                placeholder="Date of birth in format yyyy-mm-dd"
                name="dateOfBirth"
                type="text"
                onChange={(e) => this.valueInputChanged(e)}
              />
              <Button style={{ marginTop: "25px" }} onClick={this.create}>
                Add candidate
              </Button>
            </Form>
          </Col>
          <Col></Col>
        </Row>    
      </>
    );
  }
}

export default withNavigation(AddCandidate);