using UnityEngine;

public class DragAndDrop : MonoBehaviour
{
    private bool isDragging = false;
    private Vector3 offset;
    int cont = 0;

    private void OnMouseDown()
    {
        offset = gameObject.transform.position - GetMouseWorldPosition();
        isDragging = true;
        GameObject.Find("Controlador").GetComponent<Controlador>().cont++;
        Debug.Log(GameObject.Find("Controlador").GetComponent<Controlador>().cont);

    }

    private void OnMouseUp()
    {
        isDragging = false;
    }

    private void Update()
    {
        if (isDragging)
        {
            Vector3 newPosition = GetMouseWorldPosition() + offset;
            transform.position = new Vector3(newPosition.x, newPosition.y, 0); // Mantém a posição z constante
        }
    }

    private Vector3 GetMouseWorldPosition()
    {
        Vector3 mousePosition = Input.mousePosition;
        mousePosition.z = 10; // Ajuste a profundidade conforme necessário
        return Camera.main.ScreenToWorldPoint(mousePosition);
    }

    void OnCollisionEnter(Collision collision){
        
    }
}
