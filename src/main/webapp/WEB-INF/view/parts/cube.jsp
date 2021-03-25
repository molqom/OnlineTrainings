<%--
  Created by IntelliJ IDEA.
  User: andrejlazarev
  Date: 19/12/2020
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>

    <div class="container">
        <div class="cube">
            <div class="side front">Java</div>
            <div class="side back">XML</div>
            <div class="side left">CSS</div>
            <div class="side right">HTML</div>
            <div class="side top">JS</div>
            <div class="side bottom">SQL</div>
        </div>
    </div>
    <script>
        (function (){
            let rotateY = 0,
                rotateX = 0;
            document.onmousemove = function (e) {
                if (e.movementX === 1) rotateY += 4;
                if (e.movementY === -1) rotateX -= 4;
                if (e.movementX === -1) rotateY -= 4;
                if (e.movementY === 1) rotateX += 4;

                document.querySelector('.cube').style.transform =
                    'rotateY(' + rotateY + 'deg)' +
                    'rotateX(' + rotateX + 'deg)';
            }
        })();
    </script>
</div>
