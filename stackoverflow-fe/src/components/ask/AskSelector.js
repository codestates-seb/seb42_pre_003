import styled from 'styled-components';
import { useState } from 'react';
import { ArrowDownIcon, ArrowUpIcon } from './AskIcon';
import { useBoxStore } from '../../store/askStore';

const SelectBox = styled.div`
	border: 1px solid hsl(210, 8%, 75%);
	border-radius: 0.188rem;
	ul {
		border-top: 1px solid #e1e0e0;
	}
	li {
		padding: 0.65rem 0.5rem;
		font-size: 0.75rem;
		text-align: center;
	}
`;

const SelectHeader = styled.h5`
	display: flex;
	padding: 0.65rem 0.7rem;
	justify-content: space-between;
	cursor: ${(props) => (props.able ? 'not-allowed' : 'initial')};
	strong {
		font-size: 0.75rem;
		font-weight: 600;
		color: hsl(210, 8%, 45%);
	}
	svg {
		fill: hsl(210, 8%, 45%);
	}
`;

function AskSelector({ able, name }) {
	const { actData, setActData } = useBoxStore();
	const [active, setActive] = useState(false);
	const handleSelect = () => {
		setActive(!active);
	};

	const click = (item) => {
		let obj = { ...actData[0] };
		for (let el in obj) {
			if (el === item) {
				obj[el] = true;
			} else {
				obj[el] = false;
			}
		}
		setActData(obj);
	};

	return (
		<SelectBox onClick={() => click(name)}>
			<SelectHeader able={!able} onClick={!able ? null : handleSelect}>
				<strong>Do any of these posts answer your question?</strong>
				{active ? <ArrowUpIcon /> : <ArrowDownIcon />}
			</SelectHeader>
			{active ? (
				<ul>
					<li>No duplicate questions found.</li>
				</ul>
			) : null}
		</SelectBox>
	);
}
export default AskSelector;
