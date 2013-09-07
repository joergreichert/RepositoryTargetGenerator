package de.abg.jreichert.repositorytarget.dsl.formatting;

import org.eclipse.xtext.formatting.INodeModelFormatter;
import org.eclipse.xtext.formatting.INodeModelFormatter.IFormattedRegion;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.runner.RunWith;
import org.xpect.expectation.IStringExpectation;
import org.xpect.expectation.StringExpectation;
import org.xpect.parameter.ParameterParser;
import org.xpect.runner.Xpect;
import org.xpect.runner.XpectRunner;
import org.xpect.runner.XpectTestFiles;
import org.xpect.runner.XpectTestFiles.FileRoot;
import org.xpect.setup.XpectSetup;
import org.xpect.xtext.lib.setup.ThisOffset;
import org.xpect.xtext.lib.setup.ThisResource;

import com.google.inject.Inject;

@RunWith(XpectRunner.class)
@XpectTestFiles(relativeTo = FileRoot.PROJECT, baseDir = "model/testcases/formatter", fileExtensions = "targetdef")
@XpectSetup({ XtextStandaloneSetupWithoutValidate.class })
public class TargetDefinitionFormatterTest {

	@Inject
	protected INodeModelFormatter formatter;

	@ParameterParser(syntax = "('from' offset=OFFSET 'to' to=OFFSET)?")
	@Xpect
	public void formatted(
			@StringExpectation(whitespaceSensitive = true) IStringExpectation expectation,
			@ThisResource XtextResource resource, @ThisOffset int offset,
			@ThisOffset int to) {
		ICompositeNode rootNode = resource.getParseResult().getRootNode();
		IFormattedRegion r = null;
		if (offset >= 0 && to > offset) {
			r = formatter.format(rootNode, offset, to - offset);
		} else {
			r = formatter.format(rootNode, rootNode.getOffset(),
					rootNode.getTotalLength());
		}
		String formatted = r.getFormattedText();
		if(!isWindowsEnding()) {
			formatted = formatted.replaceAll("\r\n", "\n");
		}
		formatted = formatted.replaceAll("\r\b", "\n");
		formatted = formatted + getEnding();
		expectation.assertEquals(formatted);
	}

	private String getEnding() {
		return isWindowsEnding() ? "\r" : "";
	}
	
	private boolean isWindowsEnding() {
		String ls = System.getProperty("line.separator");
		return "\r\n".equals(ls);
	}
}