import React from "react";
import {Form, Button, Row} from "react-bootstrap";
import InteNSAxios from "../../apis/InteNSAxios";
import { withNavigation } from "../../routeconf";


class AddSkill extends React.Component{
    constructor(props){
        super(props);
        
        this.state = {
            id: "",
            title: ""
        };
        this.create = this.create.bind(this);

    }
    
    titleChange(e){
        let input = e.target.value;
        this.setState({title : input}) 
    }
     create(){
              
        InteNSAxios.post("/skills", this.state)
        .then((res) => {
            alert("Skill added successfully")
           window.location.reload();
        })
        .catch(error => alert("Error. Skill not added!"))
    }

    render(){
        return (
            <>
                <Row>
                    <Form>
                    <Form.Label htmlFor="name">Title</Form.Label>
                        <Form.Control
                            placeholder="Enter new title name"
                            name="title"
                            type="text"
                            onChange={(e) => this.titleChange(e)}
              />
              <Button style={{ marginTop: "25px" }} onClick={this.create}>
                Add new skill
              </Button>
                    </Form>
                </Row>
            </>
        )
    }


}

export default withNavigation(AddSkill)