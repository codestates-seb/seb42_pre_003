import React from 'react';
import styled from 'styled-components';

const Section = styled.section`
	margin-right: 480px;

	button {
		width: auto;
		height: 35px;
		color: #39739d;
		background-color: #e1ecf4;
		border-radius: 3px;
		font-size: 13px;
		border: 1px solid #39739d;
		padding: 9px;
	}

	button:hover {
		background-color: #fff;
	}
`;

const SaveButton = () => {
	return (
		<Section>
			<button>Save custom filter</button>
		</Section>
	);
};

export default SaveButton;
