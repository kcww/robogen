import {html, LitElement} from 'lit';
import {customElement} from 'lit/decorators.js';
import '@vaadin/button';
import '@vaadin/form-layout';
import '@vaadin/vertical-layout';
import '@vaadin/upload';


@customElement('robogen-view')
export class RoboGenView extends LitElement {
  createRenderRoot() {
    return this;
  }

  render() {
    return html`
			<vaadin-vertical-layout>
				<vaadin-form-layout>
					<div>
						<h4>Upload Feature File</h4>
						<p>Maximum file size: <span id="maxFeatureFileSizeLabel"></span></p>
						<vaadin-upload id="featureFileUpload"></vaadin-upload>
					</div>
					<div>
						<h4>Upload UI XML File</h4>
						<p>Maximum file size: <span id="maxXmlFileSizeLabel"></span></p>
						<vaadin-upload id="xmlFileUpload"></vaadin-upload>
					</div>
					<div>
						<h4>Upload XSD File</h4>
						<p>Maximum file size: <span id="maxXsdFileSizeLabel"></span></p>
						<vaadin-upload id="xsdFileUpload"></vaadin-upload>
					</div>
					<vaadin-button id="submit" theme="primary">Submit All Files</vaadin-button>
				</vaadin-form-layout>
			</vaadin-vertical-layout>`;
  }
}