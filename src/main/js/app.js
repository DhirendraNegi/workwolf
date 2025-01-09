'use strict';

// tag::vars[]
const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>
const client = require('./client'); // <3>
// end::vars[]

// tag::app[]
class App extends React.Component { // <1>

	constructor(props) {
		super(props);
		this.state = {workflows: []};
	}

	componentDidMount() { // <2>
		client({method: 'GET', path: '/default/workflows'}).done(response => {
		    console.log(response);
		    data = response.entity;
			this.setState({workflows: response.entity});
			console.log(response.entity);
			console.log(this.state.workflows);
			console.log(this.state.workflows[0].name);
		});
	}

	render() { // <3>
		return (
			<WorkflowList workflows={this.state.workflows}/>
		)
	}
}
// end::app[]

// tag::employee-list[]
class WorkflowList extends React.Component{
	render() {
		const workflows = this.props.workflows.map(workflow =>
			<workflow key={workflow.id} workflow={workflow}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Workflow Id</th>
						<th>Workflow Name</th>
						<th>Workflow Tenant Id</th>
					</tr>
					{workflows}
				</tbody>
			</table>
		)
	}
}
// end::employee-list[]

// tag::employee[]
class workflow extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.workflow.id}</td>
				<td>{this.props.workflow.name}</td>
				<td>{this.props.workflow.tenantId}</td>
			</tr>
		)
	}
}
// end::employee[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]