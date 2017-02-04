package application;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

/**
 *
 */
public class DragIcon extends AnchorPane{
	
	@FXML AnchorPane root_pane;

	private DragIconType mType = null;
	private NodeLink edgeLine;

	public DragIcon() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("../resources/DragIcon.fxml")
				);
		
		fxmlLoader.setRoot(this); 
		fxmlLoader.setController(this);
		
		try { 
			fxmlLoader.load();
        
		} catch (IOException exception) {
		    throw new RuntimeException(exception);
		}

		edgeLine = new NodeLink();

		this.getChildren().add(edgeLine);

		this.setOnMouseClicked(event->{
			if(event.getButton() == MouseButton.SECONDARY)
			{
				Point2D p = new Point2D(
						this.getScene().getX() + (getWidth() / 2.0),
						this.getScene().getX() + (getHeight() / 2.0)
				);

				edgeLine.setStart(p);

				this.getParent().setOnMouseMoved(ev->{
					Point2D localCoords = sceneToLocal(new Point2D(ev.getX()+108, ev.getY()));
					edgeLine.setEnd(localCoords);
				});
			}
		});

		Point2D p = new Point2D(
				this.getLayoutX() + (getWidth() / 2.0),
				this.getLayoutY() + (getHeight() / 2.0)
		);

		edgeLine.setStart(p);
	}
	
	@FXML
	private void initialize() {

	}

	public void relocateToPoint (Point2D p) {

		//relocates the object to a point that has been converted to
		//scene coordinates
		Point2D localCoords = getParent().sceneToLocal(p);
		
		relocate ( 
				(int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
				(int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
			);
	}
	
	public DragIconType getType () { return mType; }
	
	public void setType (DragIconType type) {
		
		mType = type;
		
		getStyleClass().clear();
		getStyleClass().add("dragicon");
		
		switch (mType) {
		
		case blue:
			getStyleClass().add("icon-blue");
		break;

		case red:
			getStyleClass().add("icon-red");			
		break;

		case green:
			getStyleClass().add("icon-green");
		break;

		case grey:
			getStyleClass().add("icon-grey");
		break;

		case purple:
			getStyleClass().add("icon-purple");
		break;

		case yellow:
			getStyleClass().add("icon-yellow");
		break;

		case black:
			getStyleClass().add("icon-black");
		break;
		
		default:
		break;
		}
	}
}
