package com.example.demopella4;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.ar.core.Anchor;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.collision.Ray;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;



public class MainActivity extends AppCompatActivity {

    Config.PlaneFindingMode HORIZONTAL;
    private ArFragment arFragment;
    private ModelRenderable cubo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);


       /* ModelRenderable.builder()
                .setSource(this, Uri.parse("cubo.sfb"))
                .build()
                .thenAccept(modelRenderable -> pit3 = modelRenderable);
        */
        ModelRenderable.builder()
                .setSource(this, Uri.parse("puertaPella.sfb"))
                .build()
                .thenAccept(modelRenderable -> cubo = modelRenderable);


        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();
            transformableRenderables(anchor);
        });

    }
    private Node crear_nodos(){
        Node nodo = new Node();
       // pit_nodo.setRenderable(pit3);

        Node cubo_nodo = new Node();
        cubo_nodo.setParent(nodo);
        cubo_nodo.setLocalPosition(
                new Vector3(0.0f, 0.0f, 0.0f)
        );
        cubo_nodo.setRenderable(cubo);





        return nodo;

    }

    private void transformableRenderables(Anchor anchor){

        Node x = crear_nodos();



        AnchorNode anchorNode = new AnchorNode(anchor);

        anchorNode.setParent(arFragment.getArSceneView().getScene());

        TransformableNode cube = new TransformableNode(arFragment.getTransformationSystem());
        cube.getScaleController().setMaxScale(0.55f);
        cube.getScaleController().setMinScale(0.2f);
        cube.setParent(anchorNode);
        x.setParent(cube);

    }
}
